package com.example.boardex1.services;

import com.example.boardex1.domain.Board;
import com.example.boardex1.repositories.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Board> findBoards(){
        return boardRepository.findAll();
    }

    public Optional<Board> findBoardById(Long id){
        return boardRepository.findById(id);
    }

    public Board saveBoard(Board board){
        return boardRepository.save(board);
    }

    public void deleteBoard(Long id){
        boardRepository.deleteById(id);
    }
}
