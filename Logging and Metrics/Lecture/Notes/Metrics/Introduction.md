# Introduction to Metrics

- As you know, actuators are endpoints that contain information about our
application

- One of those endpoints is `metrics`

## Categories of Metrics

1. `Application` specific to your app

2. `System` specific to the underlying infrastructure of your app

3. `Business` impact of your app on user registrations, conversion rates,
sales, etc.

## Using Metrics

- *Micrometer* is a vender-neutral tool that collects, records and exposes
metrics

- The exposed metrics can be consumed by other tools to visualize, monitor
and/or alert users

- *Prometheus* is an alerting and monitoring tool that also visualizes the
metrics data

- Whereas *Grafana* is only a visualization tool

- We will use docker to run both instead of installing them on our system

- Download docker, write up the config files for docker and Prometheus/Grafana
and you're ready to go

- Docker will automatically download the relevant images
