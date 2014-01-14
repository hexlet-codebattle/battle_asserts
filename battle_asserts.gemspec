# -*- encoding: utf-8 -*-
$:.push File.expand_path("../lib", __FILE__)
require "battle_asserts/version"

Gem::Specification.new do |s|
  s.name        = "battle_asserts"
  s.version     = BattleAsserts::VERSION
  s.platform    = Gem::Platform::RUBY
  s.authors     = ["Mokevnin Kirill"]
  s.email       = ["mokevnin@gmail.com"]
  s.homepage    = "http://github.com/kaize/battle_asserts"
  s.summary     = %q{}
  s.description = %q{}

  s.files         = `git ls-files`.split("\n")
  s.test_files    = `git ls-files -- {test,spec,features}/*`.split("\n")
  s.executables   = `git ls-files -- bin/*`.split("\n").map{ |f| File.basename(f) }
  s.require_paths = ["lib"]

  # specify any dependencies here; for example:
  # s.add_development_dependency "rspec"
  # s.add_runtime_dependency "rake"
end
