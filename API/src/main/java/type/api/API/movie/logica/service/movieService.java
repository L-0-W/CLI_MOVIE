package type.api.API.movie.logica.service;

import org.springframework.stereotype.Service;
import type.api.API.movie.dados.repository.movieRepository;

@Service
public class movieService {

    public void createDB() {
        movieRepository.con();
    }
}
