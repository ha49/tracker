package com.example.demo.web;

import com.example.demo.entity.ComboContentFlx;
import com.example.demo.repository.ComboContentFlxRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/combocontent")
public class ComboContentFlxController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LinkFlxController.class);
    private ComboContentFlxRepository comboContentFlxRepository;

    public ComboContentFlxController(ComboContentFlxRepository comboContentFlxRepository) {
        this.comboContentFlxRepository = comboContentFlxRepository;
    }

    @GetMapping("/getall")
    public Iterable<ComboContentFlx> getAllComboContent() {
        LOGGER.info("combocontent/getall ☺");
        return comboContentFlxRepository.findAll();
    }

    @GetMapping("/get/{name}")
    public Iterable<ComboContentFlx> getComboContentsByName(@PathVariable String name) {
        LOGGER.info("link/get/" + name + " ☺");

        Iterable<ComboContentFlx> comboContents = comboContentFlxRepository.findByName(name);
        return comboContents;
    }

    @PostMapping("/new")
    public ComboContentFlx addNewComboContent(@RequestBody ComboContentFlx comboContentFlx) {
        LOGGER.info("combocontent/new/ ☺");

        return comboContentFlxRepository.save(comboContentFlx);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteComboContent(@PathVariable int id) {
        LOGGER.info("combocontent/delete/" + id + " ☺");
        ComboContentFlx foundCombpContent = verifyData(id);
        comboContentFlxRepository.delete(foundCombpContent);

    }

    private ComboContentFlx verifyData(int id) throws NoSuchElementException {
        return comboContentFlxRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Link with id " + id + " does not exist "));
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return400(NoSuchElementException ex) {
        return ex.getMessage();
    }
}
