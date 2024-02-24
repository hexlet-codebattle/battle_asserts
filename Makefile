generate: clean
	clojure  -M:generate-asserts

generate-template:
	clojure -X:generate-template

clean:
	rm -rf issues/*

fix-format:
	clojure -M:cljfmt-fix
	clojure -M:kibit -- --replace

checks: check-format check-kondo check-eastwood check-kibit

check-format:
	clojure -M:cljfmt-check

check-kibit:
	clojure -M:kibit

check-eastwood:
	clojure -M:eastwood

check-kondo:
	clj-kondo --lint src test

check-translations:
	clojure -X:check-translations

check-tags:
	clojure -X:check-tags

collect-tags:
	clojure -X:collect-tags

collect-disabled:
	clojure -X:collect-disabled

check-generators-and-solutions:
	clojure -X:check-generators-and-solutions

check-outdated-deps:
	clojure -Sdeps '{:deps {com.github.liquidz/antq {:mvn/version "RELEASE"}}}' -M -m antq.core

test:
	clojure -M:test

release: generate
	tar -czf issues.tar.gz issues/*

.PHONY: test issues
