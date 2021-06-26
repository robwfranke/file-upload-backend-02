package nl.lotrac.bv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nl.lotrac.bv.model.FileDB;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {

    public abstract void  deleteFileById(String id);

}