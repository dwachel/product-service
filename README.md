# product-service

## Hexagonal architecture

### Project structure

- product
    - domain
      - model
      - port
        - inbound
        - outbound
      - service
    - infrastructure
      - adapter
        - input
        - output
      - config
      - exception
      - http
      - mapper
      - model