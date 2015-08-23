generate:
	lein run

check-style:
	lein kibit

test:
	lein trampoline test

.PHONY: test
