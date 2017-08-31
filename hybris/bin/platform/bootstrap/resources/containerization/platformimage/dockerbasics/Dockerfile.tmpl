FROM marcielmj/java-8-alpine
RUN apk add --update libstdc++ && \
    rm -rf /etc/apk/keys/sgerrand.rsa.pub /tmp/* /var/cache/apk/*
ADD binaries/ /opt/
ADD aspects /opt/aspects
ADD startup.sh /opt/startup/
RUN chmod +x /opt/startup/startup.sh && \
    chmod +x /opt/tomcat/bin/catalina.sh && \
    chmod +x /opt/ytools/*.sh
ENV CATALINA_SECURITY_OPTS=-Djava.security.egd=file:/dev/./urandom
ENV CATALINA_MEMORY_OPTS=-Xms2G\ -Xmx2G
ENV HTTPS_PORT=8088
ENV AJP_PORT=8009
ENV KEYSTORE_LOCATION=/etc/ssl/certs/hybris/keystore
ENV KEYSTORE_PASSWORD=123456
ENV PLATFORM_HOME=/opt/hybris/bin/platform/
ENV WAIT_FOR=""
ENV JVM_ROUTE=""
ENV PATH="/opt/ytools:${PATH}"
ENTRYPOINT ["/opt/startup/startup.sh"]
