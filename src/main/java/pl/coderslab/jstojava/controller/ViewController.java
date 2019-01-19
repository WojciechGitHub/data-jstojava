package pl.coderslab.jstojava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.jstojava.dto.DataContainerDto;
import pl.coderslab.jstojava.dto.DataDto;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/view")
public class ViewController {

    @GetMapping
    public String view() {
        return "view";
    }

    @PostMapping("/container")
    @ResponseBody
    public String post(@RequestBody DataContainerDto dataContainer) {
        dataContainer.getData().stream()
                .map(DataDto::toString)
                .forEach(System.out::println);

        return "DONE";
    }

    @PostMapping("/list")
    @ResponseBody
    public String post(@RequestBody List<DataDto> data) {
        data.stream()
                .map(DataDto::toString)
                .forEach(System.out::println);

        return "DONE!";
    }
}
