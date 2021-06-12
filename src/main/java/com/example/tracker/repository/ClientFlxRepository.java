package com.example.tracker.repository;


import com.example.tracker.entity.ClientFlx;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public interface ClientFlxRepository extends CrudRepository<ClientFlx, Long> {
    ClientFlx findByLastName(String lastName);

    ClientFlx getClientFlxById(Long id);


    ClientFlx getClientFlxByUserFlx_Username(String username);


}
