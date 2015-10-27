package pl.aborek.demo.tasklist.tag.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.aborek.demo.tasklist.tag.app.command.CreateTag;
import pl.aborek.demo.tasklist.tag.domain.Tag;
import pl.aborek.demo.tasklist.tag.persistance.TagRepository;

import java.util.List;

@Service
public class TagService {

    private final TagRepository repository;

    @Autowired
    public TagService(TagRepository repository) {
        this.repository = repository;
    }

    public Tag create(CreateTag createTag) {
        return repository.save(createTag.getTag());
    }

    public List<Tag> findAll() {
        return repository.findAll();
    }

    public Page<Tag> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

}
