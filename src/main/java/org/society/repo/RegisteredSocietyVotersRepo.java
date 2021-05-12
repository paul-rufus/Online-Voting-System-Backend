package org.society.repo;

import org.society.entities.RegisteredSocietyVoters;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisteredSocietyVotersRepo extends JpaRepository<RegisteredSocietyVoters, Integer> {
}