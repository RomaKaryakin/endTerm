package kz.karyakin.iitu.library.serivce;

import kz.karyakin.iitu.library.entity.Broadcasters;
import kz.karyakin.iitu.library.repository.BroadcastersRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BroadcastersService {

    private final BroadcastersRepository broadcastersRepository;

    public BroadcastersService(BroadcastersRepository broadcastersRepository) {
        this.broadcastersRepository = broadcastersRepository;
    }

    public List<Broadcasters> showAllBroadcasters(){
        return broadcastersRepository.findAll();
    }

    public Optional<Broadcasters> getBroadcastersById(Long id){
        return broadcastersRepository.findById(id);
    }

}
