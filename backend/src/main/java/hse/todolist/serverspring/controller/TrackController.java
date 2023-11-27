package hse.todolist.serverspring.controller;

import hse.todolist.databaseInteractor.ListService;
import hse.todolist.entities.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ComponentScan("hse.todolist")
@RequestMapping("/todolist/list")
@RequiredArgsConstructor
public class TrackController {

    private final ListService trackService;

    @GetMapping("/getListInfo")
    ResponseEntity<List> getTrackInfo(
            @RequestParam int listId) {
        List track = trackService.getListWithListId(listId);
        if (track == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(track, HttpStatus.OK);
        }
    }

    @GetMapping("/getAll")
    ResponseEntity<java.util.List<List>> getAll() {
        java.util.List<List> list = trackService.getAllLists();
        if (list == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }

}
