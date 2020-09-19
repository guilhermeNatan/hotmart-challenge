package com.desafio.hotmart.reuse.util;

import lombok.Getter;
import lombok.Setter;

public enum RequestStatus {
    SUCCESS("ok");

    @Getter
    @Setter
    private String descricao;
    RequestStatus(String status) {
        this.descricao = status;
    }

}
