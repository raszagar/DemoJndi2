package es.jose.config;

import org.springframework.stereotype.Component;

@Component
public class JndiPropertyHolder {
    private String jndiName;

    public String getJndiName() {
        return this.jndiName;
    }

    public void setJndiName(final String jndiName) {
        this.jndiName = jndiName;
    }

}
