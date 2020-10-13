package pl.gladis.seo_report.controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.gladis.seo_report.models.SeoElements;

import java.io.IOException;

@Controller
public class controller {

    private SeoElements elements = new SeoElements();

    @GetMapping("/")
    public String setUrl(Model model) {
        model.addAttribute("elements", elements);
        return "index";
    }

    @PostMapping
    public String setSeo(@ModelAttribute SeoElements elements, Model model) throws IOException {
        model.addAttribute("elements", elements);
        Document doc = Jsoup.connect(elements.getUrl()).get();

        elements.setTitle(doc.title());
        elements.setHeaders(getStringHeaders(doc));
        elements.setCanonicalTag(getStringCanonicalTag(doc));
        elements.setAltInImages(getStringAltInImages(doc));
        elements.setMetaDescription(getStringMetaDescription(doc));

        return "seo";
    }

    public String getStringHeaders(Document doc) {
        Elements h1Elements = doc.select("h1");
        int h1Count = h1Elements.size();
        Elements h2Elements = doc.select("h2");
        int h2Count = h2Elements.size();
        Elements h3Elements = doc.select("h3");
        int h3Count = h3Elements.size();
        Elements h4Elements = doc.select("h4");
        int h4Count = h4Elements.size();
        Elements h5Elements = doc.select("h5");
        int h5Count = h5Elements.size();
        Elements h6Elements = doc.select("h6");
        int h6Count = h6Elements.size();

        String result = "H1: " + Integer.toString(h1Count) +
                        "<br>H2: " + Integer.toString(h2Count) +
                        "<br>H3: " + Integer.toString(h3Count) +
                        "<br>H4: " + Integer.toString(h4Count) +
                        "<br>H5: " + Integer.toString(h5Count) +
                        "<br>H6: " + Integer.toString(h6Count);
        return result;
    }

    public String getStringCanonicalTag(Document doc) {
        Elements canonical = doc.select("link[rel=canonical]");
        String result;
        if (canonical.size() > 0)
            result = "Canonical tag exist";
        else
            result = "Canonical tag don't exist";
        return result;
    }

    public String getStringAltInImages(Document doc) {
        String result="";
        Elements allImages = doc.select("img");
        int countAllImages = allImages.size();
        Elements imagesWithAlt = doc.select("img[alt]");
        int countImagesWithAlt = imagesWithAlt.size();
        int countImagesWithoutAlt = countAllImages - countImagesWithAlt;
        Elements imagesWidthEmptyAlt = doc.select("img[alt='']");
        int countImagesWidthEmptyAlt = imagesWidthEmptyAlt.size();
        if (countImagesWithoutAlt == 0)
            result += "All images have alt attribute<br>";
        else
            result += "Not all images have alt attribute<br>";

        result += "All images: " + Integer.toString(countAllImages) +
                "<br>Images with alt attribute: " + Integer.toString(countImagesWithAlt) +
                "<br>Images without alt attribute: " + Integer.toString(countImagesWithoutAlt) +
                "<br>Images with empty alt attribute: " + Integer.toString(countImagesWidthEmptyAlt);

        return result;
    }

    public String getStringMetaDescription(Document doc) {
        Elements desciptions = doc.select("meta[name=description]");
        String result = "";
        for (Element descption : desciptions) {
            result = descption.attr("content");
        }
        if (result == "")
            result = "NO DESCRIPTION";
        return result;
    }
}
