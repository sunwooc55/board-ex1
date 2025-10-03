package com.example.boardex1.controllers;

import com.example.boardex1.domain.Board;
import com.example.boardex1.services.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService){
        this.boardService=boardService;
    }

    @GetMapping("/")
    public String list(Model model){
        List<Board> boards = boardService.findBoards();
        model.addAttribute("boards", boards);
        return "boards/list";
    }

    @GetMapping("/post")
    public String writeForm(){
        return "boards/write";
    }

    @PostMapping("/post")
    public String write(Board board){
        boardService.saveBoard(board);
        return "redirect:/";
    }

    @GetMapping("/post/{id}")
    public String detail(@PathVariable Long id, Model model){
        boardService.findBoardById(id)
                .ifPresent(board -> model.addAttribute("board", board));
        return "boards/detail";
    }

    @PostMapping("/post/edit/{id}")
    public String editForm(@PathVariable Long id, Model model){
        boardService.findBoardById(id).ifPresent(board->model.addAttribute("board", board));
        return "boards/edit";
    }

    @PostMapping("/post/edit/{id}")
    public String edit(@PathVariable Long id, Board updatedBoard){
        updatedBoard.setId(id);
        boardService.saveBoard(updatedBoard);
        return "redirect:/post/" + id;
    }

    @PostMapping("/post/delete/{id}")
    public String delete(@PathVariable Long id){
        boardService.deleteBoard(id);
        return "redirect:/";
    }
}
