package pl.aborek.demo.tasklist.tag.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.aborek.demo.tasklist.tag.app.TagService;
import pl.aborek.demo.tasklist.tag.domain.Tag;

import java.util.List;

import static pl.aborek.demo.tasklist.util.PageRequestUtil.createPageRequest;

@Controller
@RequestMapping(path = "/tags")
public class TagController {

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<List<Tag>> list(@RequestParam(required = false) Integer pageSize, @RequestParam(required = false) Integer pageNumber) {
        return new ResponseEntity(tagService.findAll(createPageRequest(pageSize, pageNumber)), HttpStatus.OK);
    }

}
