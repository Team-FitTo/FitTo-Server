package com.example.fittoserver.global.common.util;

import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HashIdUtil {

    private final Hashids hashids;

    public HashIdUtil(@Value("${hashid.salt}") String salt) {
        this.hashids = new Hashids(salt, 7);
    }

    public String encode(Long id) {
        return hashids.encode(id);
    }

    public Long decode(String hashId) {
        long[] decoded = hashids.decode(hashId);
        if (decoded.length == 0) {
            return null;
        }
        return decoded[0];
    }
}
