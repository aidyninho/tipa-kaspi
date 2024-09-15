package kz.aidyninho.tipakaspi.service;

import kz.aidyninho.tipakaspi.repository.LimitRepository;
import org.springframework.stereotype.Service;

@Service
public class LimitService {

    private final LimitRepository limitRepository;

    public LimitService(LimitRepository limitRepository) {
        this.limitRepository = limitRepository;
    }

    public boolean resetAllLimits() {
        return limitRepository.resetAllCurrentSum() == limitRepository.count();
    }
}
