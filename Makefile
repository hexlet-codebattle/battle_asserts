generate-from-docker: clean
	docker run -v $(CURDIR):/battle_asserts clojure /bin/bash -c 'cd /battle_asserts && lein run'

generate: clean
	lein run

clean:
	rm -rf issues/*

format:
	lein cljfmt fix

checks: check-format check-namespaces check-style

check-format:
	lein cljfmt check

check-style:
	lein kibit

check-namespaces:
	lein eastwood

test:
	lein trampoline test

release: generate
	tar -czf issues.tar.gz issues/*

.PHONY: test issues
