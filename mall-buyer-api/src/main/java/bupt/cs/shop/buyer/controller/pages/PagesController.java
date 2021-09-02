package bupt.cs.shop.buyer.controller.pages;

import bupt.cs.shop.buyer.enums.PageType;
import bupt.cs.shop.buyer.service.pages.PagesService;
import bupt.cs.shop.common.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pages")
public class PagesController {

    @Autowired
    private PagesService pagesService;

    @GetMapping("index")
    public Result index(Integer clientType) {

        return pagesService.getPageIndexData(clientType, PageType.INDEX.getCode());
    }
}