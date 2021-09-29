package io.javabrains.springbootstarter.topics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, String> {
    //JpaRepository<Entity, @Id Field>
}
