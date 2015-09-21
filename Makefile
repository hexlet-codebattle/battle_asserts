issues:
	docker run -v $(CURDIR):/battle_asserts clojure /bin/bash -c 'cd /battle_asserts && lein run'

generate: format
	lein run

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

.PHONY: test issues
