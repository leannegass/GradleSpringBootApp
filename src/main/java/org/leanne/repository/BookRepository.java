package org.leanne.repository;
import org.leanne.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookRepository extends JpaRepository<BookModel, Long> {

    List<BookModel> findByTitleContaining(String title);

}






