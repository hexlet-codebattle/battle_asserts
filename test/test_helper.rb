require "rubygems"
require 'bundler/setup'
Bundler.require

require 'wrong/adapters/minitest'
require 'yaml'

require "minitest/reporters"

Minitest::Reporters.use!

Minitest.autorun

class TestCase < Minitest::Test
  include Wrong
end
