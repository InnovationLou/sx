package xyz.ruankun.laughingspork.service;

import xyz.ruankun.laughingspork.entity.SxCorporation;

public interface SxCorporationService {
    void save(SxCorporation sxCorporation);

    void delete(SxCorporation sxCorporation);

    SxCorporation findByStuNo(String stuNo);
}
