package org.example.usecase.implement.post;

import org.example.common.UpsertStatus;
import org.example.gateway.api.PostGateway;
import org.example.model.converter.PostDTO2DConverter;
import org.example.model.dto.PostDTO;
import org.example.usecase.api.post.UpsertPostUseCase;

public class UpsertPostInteractor implements UpsertPostUseCase {
    private final PostGateway gateway;
    private final PostDTO2DConverter converter;

    public UpsertPostInteractor(PostGateway gateway,
                                   PostDTO2DConverter converter) {
        this.gateway = gateway;
        this.converter = converter;
    }

    @Override
    public UpsertStatus upsert(PostDTO dto) {
        return gateway.upsert(converter.convert(dto));
    }
}
