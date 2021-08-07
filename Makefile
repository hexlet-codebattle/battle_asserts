generate-from-docker: clean
	docker run -v $(CURDIR):/battle_asserts clojure /bin/bash -c 'cd /battle_asserts && lein run'

generate: clean
	lein run

clean:
	rm -rf issues/*

format:
	lein cljfmt fix

checks: check-format check-namespaces check-style check-kondo

check-format:
	lein cljfmt check

check-style:
	lein kibit

fix-style:
	lein kibit --replace

fix-style-interactive:
	lein kibit --replace --interactive

check-namespaces:
	lein eastwood "{:exclude-linters [:reflection]}"

check-kondo:
	clj-kondo --lint src test

check-translations:
	lein run -m battle-asserts.util-progress-tools/check-translations

check-tags:
	lein run -m battle-asserts.util-progress-tools/check-tags

collect-tags:
	lein run -m battle-asserts.util-progress-tools/collect-tags

collect-disabled:
	lein run -m battle-asserts.util-progress-tools/collect-disabled

check-generators-and-solutions:
	lein run -m battle-asserts.util-progress-tools/check-generators-and-solutions

test:
	bin/kaocha

release: generate
	tar -czf issues.tar.gz issues/*

.PHONY: test issues
