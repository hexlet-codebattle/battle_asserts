FROM clojure:latest
COPY . /usr/src/app
WORKDIR /usr/src/app
CMD ["clojure", "-m" "battle-asserts.core"]
