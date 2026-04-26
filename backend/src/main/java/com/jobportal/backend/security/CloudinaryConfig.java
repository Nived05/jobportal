package com.jobportal.backend.security;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dvbhurirm",
                "api_key",    "835562342166376",
                "api_secret", "qX7YCCBXoOpfIIj56Qd3Bq-2u24"
        ));
    }
}