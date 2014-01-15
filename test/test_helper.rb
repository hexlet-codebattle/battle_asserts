require "rubygems"
require 'bundler/setup'
Bundler.require

require 'wrong/adapters/minitest'

PROJECT_ROOT = File.join(Dir.pwd)

Wrong.config.color

Minitest.autorun

class TestCase < Minitest::Test
  include Wrong
end
