package br.com.questions.service;

import br.com.questions.entity.Flag;

import java.util.List;

public interface FlagService {
    Flag insertFlag(Flag flag);
    void deleteFlag(Long id);
    void updateFlag(Long id, Flag newFlag);
    List<Flag> findAllFlag();
    Flag findById(Long id);
}
