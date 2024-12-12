FROM ubuntu:latest
LABEL authors="vlad-kondratenko"

ENTRYPOINT ["top", "-b"]