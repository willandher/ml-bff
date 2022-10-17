FROM gradle:7.1.1-jdk11  as multistage

WORKDIR /app/

COPY . .

RUN chmod +x entrypoint.sh

EXPOSE 8080

RUN adduser --system --uid 10000 --group --shell /sbin/nologin --home /app/ app
RUN chown app:app .env

USER 10000

ENTRYPOINT [ "./entrypoint.sh" ]