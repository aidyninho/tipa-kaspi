package kz.aidyninho.tipakaspi.service;

import kz.aidyninho.tipakaspi.exception.LimitNotFoundException;
import kz.aidyninho.tipakaspi.model.Limit;
import kz.aidyninho.tipakaspi.repository.LimitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LimitService {

    private final LimitRepository limitRepository;

    public LimitService(LimitRepository limitRepository) {
        this.limitRepository = limitRepository;
    }

    public boolean resetAllLimits() {
        boolean reset = limitRepository.resetAllCurrentSum() == limitRepository.count();

        log.warn("Limits reset");
        return reset;
    }

    public Limit save(Limit limit) {
        log.info("Saving limit: {}", limit);
        return limitRepository.save(limit);
    }

    public Limit findById(Integer id) {
        return limitRepository.findById(id).orElseThrow(LimitNotFoundException::new);
    }
}
