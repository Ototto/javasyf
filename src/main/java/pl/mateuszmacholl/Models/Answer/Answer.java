package pl.mateuszmacholl.Models.Answer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.mateuszmacholl.Models.Post.Post;
import pl.mateuszmacholl.Models.User.User;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created by ggere on 21.03.2018.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "answer")
public class Answer {
	@Id
	@GeneratedValue
	private Integer id;

	@NotNull
	@NotEmpty
	@Lob
	private String content;

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User author;

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "post_id")
	private Post post;
}
