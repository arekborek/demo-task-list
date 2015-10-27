package pl.aborek.demo.tasklist.tag.app.command;

import pl.aborek.demo.tasklist.tag.domain.Tag;

public class CreateTag {

    private final String tag;

    public CreateTag(String tag) {
        this.tag = tag;
    }

    public Tag getTag() {
        return new Tag(tag);
    }
}
