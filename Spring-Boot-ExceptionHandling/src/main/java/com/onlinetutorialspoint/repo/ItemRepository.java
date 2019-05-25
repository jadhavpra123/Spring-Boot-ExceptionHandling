package com.onlinetutorialspoint.repo;
 
import com.onlinetutorialspoint.exception.ItemNotFoundException;
import com.onlinetutorialspoint.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("itemRepository")
public interface ItemRepository extends JpaRepository<Item, Long> 
{ 
	Item findById(long id);
 
}
 