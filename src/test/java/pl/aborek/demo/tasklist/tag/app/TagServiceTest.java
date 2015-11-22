package pl.aborek.demo.tasklist.tag.app;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.aborek.demo.tasklist.DemoTaskListApplication;
import pl.aborek.demo.tasklist.tag.app.command.CreateTag;
import pl.aborek.demo.tasklist.tag.domain.Tag;

import static org.fest.assertions.Assertions.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoTaskListApplication.class)
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TagServiceTest {

    @Autowired
    TagService tagService;

    @Test
    @Ignore
    public void mustListAllTags() {
        //given

        //when
        Page<Tag> tags = tagService.findAll(new PageRequest(0, 2));

        //then
        assertThat(tags.getTotalElements()).isEqualTo(5);
        assertThat(tags.getTotalPages()).isEqualTo(3);
    }

    @Test
    @Ignore
    public void mustCreateTag() {
        //given

        //when
        Tag created = tagService.create(new CreateTag("tag"));

        //then
        assertThat(tagService.findAll()).contains(created);
    }

}