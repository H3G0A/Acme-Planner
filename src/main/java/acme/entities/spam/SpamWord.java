package acme.entities.spam;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class SpamWord extends DomainEntity {
    protected static final long serialVersionUID = 1L;

    @OneToMany
    private List<Word> spamWords;

    protected double threshold;
}