require 'bundler/setup'
Bundler.require

require 'wrong/adapters/minitest'
require 'yaml'

Minitest.autorun

require "minitest/reporters"
Minitest::Reporters.use!
