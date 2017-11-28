package com.lession.spring.service;

import org.springframework.security.access.prepost.PreAuthorize;

public interface BookService {

    // 在访问此方法前，进行权限设置
    @PreAuthorize("hasAuthority('xxx')")
    void getKongzhiUrlByZhujie(Long id);

}
