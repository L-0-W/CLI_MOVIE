package type.api.movie.logica.service;

import org.springframework.stereotype.Service;
import type.api.movie.dados.repository.movieRepository;

@Service
public class movieService {

    public void createDB() {
        movieRepository.con();
    }
}
