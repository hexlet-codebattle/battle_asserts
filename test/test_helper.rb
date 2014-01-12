require "rubygems"
require 'bundler/setup'
Bundler.require

require 'yaml'
require 'wrong/adapters/minitest'

PROJECT_ROOT = File.join(Dir.pwd)

Wrong.config.color

Minitest.autorun

class TestCase < Minitest::Test
  include Wrong
end

class IssueIterator
  include Enumerable

  def initialize(dir)
    if !dir || !File.directory?(dir)
      raise ArgumentError, "'#{dir}' isn't directory"
    end
    @dir = dir
  end

  def each
    Dir.foreach(@dir) do |file_name|
      full_path = File.join(@dir, file_name)
      next if File.directory?(full_path)
      issue = YAML.load_file(full_path)
      issue["name"] = file_name.split(".").first

      yield issue
    end
  end
end
