package pl.mateuszmacholl.Models.Post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import pl.mateuszmacholl.Models.Answer.Answer;
import pl.mateuszmacholl.Models.User.User;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * Created by ggere on 21.03.2018.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "post")
public class Post {
	@Id
	@GeneratedValue
	private Integer id;

	@NotNull
	@NotEmpty
	private String title;

	@NotNull
	@NotEmpty
	private String content;

	@NotNull
	@NotEmpty
	@DateTimeFormat
	private Date date;

	private Boolean solved = false;

	@ElementCollection
	private List<String> tags = new ArrayList<>();

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User author;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, mappedBy = "post")
	@JsonIgnore
	private Set<Answer> answers = new HashSet<Answer>();
}
