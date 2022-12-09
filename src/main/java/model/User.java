package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
@Getter
@EqualsAndHashCode
public class User {

    Long id;
    String name;
    String email;
    String address;
    List<Book> rentedBooks;

}
