package pl.minicode.targowiska.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public abstract interface IPageableCommonService<T> extends ICommonService<T> {

	Page<T> findAll(Pageable pageable);
}
