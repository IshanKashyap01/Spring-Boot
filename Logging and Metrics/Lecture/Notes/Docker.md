# Docker Configuration

Following is your `docker-compose.yml` file:

```yml
version: '3.7'
services:
  prometheus:
    image: prom/prometheus:v2.35.0
    container_name: prometheus
    volumes:
      - ./prometheus/prometheus.yml:/etc/prometheus/prometheus.yml
    ports:
      - 9090:9090

  grafana:
    image: grafana/grafana:9.5.2
    container_name: grafana
    volumes:
      - ./grafana/provisioning/datasources:/etc/grafana/provisioning/datasources
    ports:
      - 3000:3000
    restart: unless-stopped
```

- Once you've written the file, use the command `docker compose up` to get
everything up and running
