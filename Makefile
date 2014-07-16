generate:
	lein run

test:
	LEIN_FAST_TRAMPOLINE=true lein trampoline test

.PHONY: test
