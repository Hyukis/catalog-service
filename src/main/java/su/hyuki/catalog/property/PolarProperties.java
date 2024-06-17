package su.hyuki.property;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "polar")
public class PolarProperties {

    private String greeting;
}
